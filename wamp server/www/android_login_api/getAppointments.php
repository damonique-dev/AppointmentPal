<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_POST['doctorName'])) {

	$doc = $_POST['doctorName'];

	$appointments = $db->getAppointmentsByDoctor($doc);

if ($doc != false) {
		// user is found
		$response["error"] = FALSE:
		$response["appointments"] = $appointments["datetime"];
		echo json_encode($response);

} else {
		// user is not found with the credentials
		$response["error"] = TRUE;
		$response["error_msg"] = "Login credentials are wrong. Please try again!"
		echo json_encode($response);
}
?>