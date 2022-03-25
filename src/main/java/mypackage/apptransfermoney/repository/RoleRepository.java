package mypackage.apptransfermoney.repository;

import mypackage.apptransfermoney.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select * from role where role_name=?1",nativeQuery = true)
    Role findByRoleName(String roleName);
}
