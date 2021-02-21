package rbac.server.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import rbac.server.models.Permission;


public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
