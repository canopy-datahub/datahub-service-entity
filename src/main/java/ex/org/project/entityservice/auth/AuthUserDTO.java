package ex.org.project.entityservice.auth;

import java.util.List;

record AuthUserDTO(
        Integer id,
        String email,
        List<String> roles,
        String sessionId,
        String dbGapPermissions,
        String status,
        Boolean internalUser
){}
