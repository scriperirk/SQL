set DB_URL=jdbc.url=jdbc:mysql://localhost:3306/scr_mysql
set DB_USER=scriper
set DB_PASS=123
docker exec -i aqa4-sql bash -c "mysql -u %DB_USER% %DB_USER% --password=%DB_PASS%" < data_clean.sql
java -jar .\artifacts\app-deadline.jar