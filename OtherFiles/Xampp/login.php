<?php
$con=mysqli_connect("localhost","admin","admin","users");


$usr= $_POST['username'];
$pwdIn= $_POST['password'];

$q=mysqli_query($con,"SELECT password FROM userlist WHERE username ='$usr'");

while($e=mysqli_fetch_assoc($q)){
	global $pwd;	
	$output[]=$e;
	$pwd = $e['password'];
}
if($pwd == $pwdIn)
	print("true");
else
	print("false");

mysqli_close($con);
?>