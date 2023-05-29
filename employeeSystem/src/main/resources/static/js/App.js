// click event for add btn
$('#addEmpBtn').click(function() {
	// when click show modal
	window.$('#modal-form-add').modal('show');
})
// click event for update btn
$('.updateEmpBtn').click(function() {
	// when click show modal
	window.$('#modal-form-update').modal('show');
})

// search event
$('#findBtn').click(function () {
	// get mapping
	$.ajax({
		type: 'GET',
		url: '/employee/select', 
		data: {
			'search': $('#search').val()
		},
		success: function (data) {
			$('#employeTable').html(data)
		},
		error: function (err) {
			console.log(err)
			alert('Error, Please Retry! ')
		}
	})
})

$('#addSubmitBtn').click(function(){
	var firstName = $('#addFirstName').val()
	var lastName = $('#addLastName').val()
	var email = $('#addEmail').val()
	var phoneNumber = $('#addPhoneNumber').val()

	console.log(firstName,lastName,email,phoneNumber);
	//Not null validation
	if (firstName.length == 0){
		alert("First name cannot be empty ")
	}else if(lastName.length == 0){
		alert("Last name cannot be empty ")
	}else if (email.length == 0){
		alert("Email cannot be empty ")
	}else if (phoneNumber.length == 0){
		alert("mail cannot be empty ")
	}else{
		$.ajax({
			type:"POST",
			url:"/employee/insert",
			data: {
				'firstName': firstName,
				'lastName': lastName,
				'email': email,
				'phoneNumber': phoneNumber
			},
			success:function(data){
				console.log(firstName,lastName,email,phoneNumber);
				//close modal
				$('#modal-form-add').modal('hide')
				//empty the model
				document.getElementById("addform").reset()
				$('#employeTable').html(data)
			},
			error: function(err){
				console.log(firstName);
				console.log(err);
				alert("Error, Please try again! ")
			}
		})
	}
})

$('#updateSubmitBtn').click(function(){
	var id = $('#updateEmployeeId').val()
	var firstName = $('#updateEmployeeFirstName').val()
	var lastName = $('#updateEmployeeLastName').val()
	var email = $('#updateEmployeeEmail').val()
	var phoneNumber = $('#updateEmployeePhoneNumber').val()

	console.log(firstName,lastName,email,phoneNumber);
	//Not null validation
	if (firstName.length == 0){
		alert("First name cannot be empty ")
	}else if(lastName.length == 0){
		alert("Last name cannot be empty ")
	}else if (email.length == 0){
		alert("Email cannot be empty ")
	}else if (phoneNumber.length == 0){
		alert("mail cannot be empty ")
	}else{
		$.ajax({
			type:"POST",
			url:"/employee/update",
			data: {
				"id":id,
				'firstName': firstName,
				'lastName': lastName,
				'email': email,
				'phoneNumber': phoneNumber
			},
			success:function(data){
				
				//close modal
				$('#modal-form-update').modal('hide')
				//empty the model
				document.getElementById("updateform").reset()
				$('#employeTable').html(data)
			},
			error: function(err){
				
				console.log(err);
				alert("Error, Please try again! ")
			}
		})
	}
})
