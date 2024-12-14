package io.github.hikarimiku.checkpointspot.taskservice.repo;

import io.github.hikarimiku.checkpointspot.taskservice.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID>, JpaSpecificationExecutor<TaskEntity>{
    @Query("SELECT p, (SELECT COUNT(t) FROM TaskEntity t WHERE t.parentTask.id = p.id), " +
            "(SELECT COUNT(l) FROM TaskEntity l WHERE l.parentTask.id = p.id AND l.status ='DONE') , " +
            "(SELECT COUNT(m) FROM TaskEntity m WHERE m.parentTask.id = p.id AND m.status ='COMPLETED') , " +
            "(SELECT COUNT(n) FROM TaskEntity n)  " +
            "FROM TaskEntity p WHERE UPPER(p.name) LIKE %:name% and UPPER(p.status) LIKE %:status%")
    Page<Object[]> search(@Param("name") String name, @Param("status") String status, Pageable pageable);
}
