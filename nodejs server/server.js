var app   = require('express')();
var crypto = require("crypto");
var http = require('http').Server(app);
var mysql = require('mysql');
var bodyParser = require("body-parser");
var connection = mysql.createConnection({
		host     : 'localhost',
		user     : 'root',
		password : 'fuck',
		database : 'appointmentpal',
	});
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

	


app.get('/doctors',function(req,res){
	var data = {
		"error":1,
		"Doctors":""
	};
	
	connection.query("SELECT NAME from doctor",function(err, rows, fields){
		if(rows.length != 0){
			data["error"] = 0;
			data["Doctors"] = rows;
			res.json(data);
		}else{
			data["Doctors"] = 'No doctors Found..';
			res.json(data);
		}
	});
});

app.post('/doctor',function(req,res){
	var nm = req.body.doctorname;
	var data = {
		"error":1,
		"Doctors":"",
	}

	if(!!nm){
		connection.query("SELECT * from doctor WHERE name = ?",[nm],function(err, rows, fields){

			if(!!err){
				data["Doctors"] = "Error Adding User";
			}else{
				data["error"] = 0;
				data["Doctors"] = rows;

			}
			res.json(data);
		});
	}else{
		data["Doctors"] = "Please provide all required data (i.e : Bookname, Authorname, Price)";
		res.json(data);
	}

});

app.post('/login',function(req,res){
	console.log(req.body);
	var email = req.body.email;
	var password = req.body.password;
	var data = {
		"error":1,
		"User":"",
		"uuid":"",
		"nm":""
	};

	connection.query("SELECT uuid,name,hash FROM user WHERE email = ?",[email],function(err, rows, fields){
		
		if(!!err){
			data["User"] = "error connecting"
		}
		else{

     		var uuid = rows[0].uuid;
     		var hash = rows[0].hash;
     		var nm = rows[0].nm;

   			if(hash != crypto.createHash("md5").update(password).digest('hex')){
			console.log(finalresponse.hash);
			console.log(crypto.createHash("md5").update(password).digest('hex'));
			data["User"] = "incorrect username/password.";
			res.json(data);
		}

		else{
			data["error"]=0;
			data["User"] = nm;
			data["uuid"] = uuid;
			res.json(data);
		}
	}

	});

});


app.post('/register',function(req,res){
	console.log(req.body);
	var uuid = require('node-uuid');
	var uuid1 = uuid.v1();
	var name = req.body.username;
	var email = req.body.email;
	var phoneno = req.body.phoneno
	var password = req.body.password;
	var data = {
		"error":1,
		"Users":"",
		"uuid":"",
		"name":"",
		"email":""
	};

	var maxtime = 0.1;

	var hash = crypto.createHash("md5").update(password).digest('hex');

     if(!!name && !!email && !!phoneno && !!hash){
				connection.query("INSERT INTO user VALUES(?,?,?,?,?)",[uuid1,name,email,phoneno,hash],function(err, rows, fields){
				if(!!err){
				data["Users"] = "Error Adding User";
			}else{
				data["error"] = 0;
				data["Users"] = "User Added Successfully";
				data["uuid"] = uuid1;
				data["name"] = name;
				data["email"] = email;
			}
			res.json(data);
		});
	}else{
		data["Users"] = "Please provide all required data (i.e : Username, email, phoneno, password";
		res.json(data);
	}
	
});







//for edit user activities


app.put('/user',function(req,res){
	var username = req.body.username;
	var email = req.body.email;
	var password = req.body.password;
	var data = {
		"error":1,
		"Users":""
	};
	if( !!username && !!email && !!password){
		connection.query("UPDATE appointmentpal SET email=?, password=? WHERE username=?",[email, password, username],function(err, rows, fields){
			if(!!err){
				data["Users"] = "Error Updating data";
			}else{
				data["error"] = 0;
				data["Users"] = "Updated Book Successfully";
			}
			res.json(data);
		});
	}else{
		data["Users"] = "Please provide all required data (i.e : id, Bookname, Authorname, Price)";
		res.json(data);
	}
});

app.delete('/user',function(req,res){
	var username = req.body.username;
	var data = {
		"error":1,
		"Users":""
	};
	if(!!username){
		connection.query("DELETE FROM appointmentpal WHERE username=?",[username],function(err, rows, fields){
			if(!!err){
				data["Users"] = "Error deleting data";
			}else{
				data["error"] = 0;
				data["Users"] = "Delete Book Successfully";
			}
			res.json(data);
		});
	}else{
		data["Users"] = "Please provide all required data (i.e : id )";
		res.json(data);
	}
});

http.listen(8080,function(){
	console.log("Connected & Listen to port 8080");
});