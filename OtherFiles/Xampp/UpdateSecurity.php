<?php
$con=mysqli_connect("localhost","admin","admin","users");

$secmode =$_POST["secmode"];
//$secmode ="Armed-Away";
//$lightstatus  = 51;

mysqli_query($con,"UPDATE users.house SET secmode ='$secmode' WHERE _id = 1" );

//if($lightstatus > 50) {

//$myfile = fopen("data.txt", "w") or die("Unable to open file!");
//$txt = "LEDON";
//fwrite($myfile, $txt);
//fclose($myfile);
//}
//else{
//$myfile = fopen("data.txt", "w") or die("Unable to open file!");
//$txt = "LEDOFF";
//fwrite($myfile, $txt);
//fclose($myfile);
//}

//$output = shell_exec('javac lightsUpdate.java') ;
//$output1 = shell_exec('java lightsUpdate') ;
//echo $output ;


//$output3 = file_get_contents('192.168.1.3/fromUserApp/lightsUpdate.php?lightID=
//$lightID&floor =$floor &lightstatus= $lightstatus') ;

mysqli_close($con);
?>