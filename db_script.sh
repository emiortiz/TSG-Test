
echo "paso 1 $1 $2"

# Conectarse al servidor MySQL
mysql -u $1 -p$2 < migration_script.sql
