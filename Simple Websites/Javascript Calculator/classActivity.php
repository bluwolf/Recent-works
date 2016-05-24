

<!DOCTYPE html>
<html>
<body>

<?php
$dbconnection = mysqli_connect("uscitp.com", "xiaohanc", "Xc19941214", "xiaohanc_classActivity");  

if (!$dbconnection) {
    exit ("Failed to connect to MySQL: " . mysqli_connect_error());
}   

$sql = "SELECT customers, 3 FROM customers ORDER BY FirstName";

$results = mysqli_query($dbconnection, $sql);

if (!$results) {
    exit ("SQL Query Error: " . mysqli_error($dbconnection));
}    

echo "If you see this statement, everything worked CORRECTLY with no errors!";
?>

</body>
</html>