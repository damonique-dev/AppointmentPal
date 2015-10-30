<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

$doctors = $db->getDoctors();

if($doctors != false){

	$response["name"] = $doctors["name"];
	echo json_encode($response);
}
else {
		// user is not found with the credentials
		$response["error"] = TRUE;
		$response["error_msg"] = "Login credentials are wrong. Please try again!"
		echo json_encode($response);
}
?>