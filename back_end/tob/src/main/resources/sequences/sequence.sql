INSERT INTO m_roles (role_id, code, role_name)
SELECT 'ROLE_ADMIN',
       'ROLE_ADMIN',
       'Administrator' WHERE NOT EXISTS (SELECT 1 FROM m_roles WHERE code='ROLE_ADMIN');

INSERT INTO m_roles (role_id, code, role_name)
SELECT 'ROLE_USER',
       'ROLE_USER',
       'User' WHERE NOT EXISTS (SELECT 1 FROM m_roles WHERE code='ROLE_USER');

INSERT INTO m_roles (role_id, code, role_name)
SELECT 'ROLE_GUEST',
       'ROLE_GUEST',
       'Guest' WHERE NOT EXISTS (SELECT 1 FROM m_roles WHERE code='ROLE_GUEST');