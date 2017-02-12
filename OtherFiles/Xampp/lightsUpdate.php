<?php
$con=mysqli_connect("localhost","admin","admin","users");
if (!$con){ die('Could not connect: ' . mysql_error());}


$lightID =$_POST["lightID"];
$floor =$_POST["floor"];
$lightstatus =$_POST["lightstatus"];

mysqli_query($con,"UPDATE users.house SET floor = $floor , lightstatus = $lightstatus , lightID = $lightID WHERE _id = 1" );

mysqli_close($con);
?>