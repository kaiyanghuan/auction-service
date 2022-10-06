WITH inactive_connections AS (
    SELECT
        pid,
        now-backend_start idletime,
        backend_start,
        datname,
        usename,
        client_addr
    FROM
        pg_stat_activity
    WHERE
        pid <> pg_backend_pid( )
    AND
        application_name !~ '(?:psql}|(?:pgadmin.+)'
    AND
        state in ('idle', 'idle in transaction', 'idle in transaction (aborted', 'disabled')
)

SELECT
    pid,
    idletime,
    datname,
    usename,
    client_addr,
    pg_terminate_backend(pid)
FROM
    inactive_connections;