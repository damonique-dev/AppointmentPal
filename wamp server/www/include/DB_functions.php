<?php

class DB_Functions {

	private $conn;

	// constructor
	function __construct() {
		require_once 'DB_Connect.php';
		// connecting to database
		$db = new Db_Connect();
		$this->conn = $db->connect();
	}

	// destructor
	function __destruct(){

	}

	/**
	* Storing new user
	* return user details
	*/
	public function storeUser($name, $email, $phoneno, $password) {
		$uuid = uniqid('', true);
		$hash = $this->hashSSHA($password);
		$encrypted_password = $hash["encrypted"]; //encrypted password
		$salt = $hash["salt"]; // salt

		$stmt = $this->conn->prepare("INSERT INTO users(unique_id, name, email, phoneno, encrypted_password, salt) VALUES(?, ?, ?, ?, ?, ?)");
		$stmt->bind_param("sssss", $uuid, $name, $email, $phoneno, $encrypted_password, $salt);
		$result = $stmt->execute();
		$stmt->close();

		// check for successful store
		if ($result) {
			$stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
			$stmt->bind_param("s", $email);
			$stmt->execute();
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();

			return $user;

		} else {

			return false;

		}

	}

	/**
	* Get user by email and password
	*/
	public function getUserByEmailAndPassword($email, $password){
		$stmt = $this->conn->prepare("SELECT * FROM PATIENT WHERE email = ?");

		$stmt->bind_param("s", $email);

		if ($stmt->execute()) {
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();
			return $user;
		} else {
			return NULL;
		}

	}

	public function getDoctors(){
		
		$stmt = $this->conn->prepare("SELECT NAME FROM doctor;");

		$stmt->execute();

		$doctors = $stmt->get_result()->fetch_assoc();

		return $doctors;
	}

	public function getAppointmentsByDoctor($doctorName){

		$stmt = $this->conn->prepare("SELECT datetime FROM appointment WHERE dname = ?");

		$stmt->bind_param("s", $doctorName);

		if($stmt->execute()){
			$appointments = $stmt->get_result()->fetch_assoc();
			$stmt->close();
			return $appointment;
		}
		else{
			return NULL;
		}

	}

	/**
	* Check if user exists or not
	*/
	public function isUserExisted($email) {
		$stmt = $this->conn->prepare("SELECT email from users WHERE email = ?")

		$stmt->bind_param("s", $email);

		$stmt->execute();

		$stmt->store_result();

		if($stmt->num_rows > 0) {
			// user existed
			$stmt->close();
			return true;
		} else {
			// user not existed
			$stmt->close();
			return false;
		}
		
	}

	/**
	* Encrypting password
	* @param password
	* returns salt and encrypted password
	*/
	public function hashSSHA($password) {

		$salt = sha1(rand());
		$salt = substr($salt, 0, 10);
		$encrypted = base64_encode(sha1($password . $salt, true) . $salt);
		$hash = array("salt" => $salt, "encrypted" => $encrypted);
		return $hash;
	}

	/**
	* Decrypting password
	* @param salt, password
	* returns hash string
	*/
	public function checkhashSSHA($salt, $password) {
 	 
 		$hash = base64_encode(sha1($password . $salt, true) . $salt);

 		return $hash;
 		
	}

}

?>