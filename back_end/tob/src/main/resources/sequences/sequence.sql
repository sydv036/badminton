INSERT INTO m_roles (role_id, code, role_name, system_delete_flag)
SELECT 'ROLE_ADMIN',
       'ROLE_ADMIN',
       'Administrator',
        false
        WHERE NOT EXISTS (SELECT 1 FROM m_roles WHERE code='ROLE_ADMIN');

INSERT INTO m_roles (role_id, code, role_name, system_delete_flag)
SELECT 'ROLE_USER',
       'ROLE_USER',
       'User',
    false
    WHERE NOT EXISTS (SELECT 1 FROM m_roles WHERE code='ROLE_USER');

INSERT INTO m_roles (role_id, code, role_name, system_delete_flag)
SELECT 'ROLE_GUEST',
       'ROLE_GUEST',
       'Guest',
    false
    WHERE NOT EXISTS (SELECT 1 FROM m_roles WHERE code='ROLE_GUEST');
