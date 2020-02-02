$(document)
		.ready(
				function() {

					$('#registerform')
							.submit(
									function(e) {
										var username = $('#username').val();
										if (username == "") {
											alert("Username can not be empty");
											return false;
										}
										var password = $('#password').val();
										if (password == "") {
											alert("Password can not be empty");
											return false;
										}
										var password2 = $('#password2').val();

										if (password != password2) {
											alert("Passowrd are not identical");
											return false;
										}

										var email = $('#email').val();

										if (email == "") {
											alert("Email can not be empty");
											return false;
										}
										var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
										if (!re.test(email)) {
											alert("Email format invalid");
											return false;
										}
										var firstname = $().val('#firstname');
										if (firstname == "") {
											alert("First Name can not be empty");
											return false;
										}
										var lastname = $().val('#lastname');
										if (lastname == "") {
											alert("Last name can not be empty");
											return false;
										}
										var nickname = $().val('#nickname');
										if (nickname == "") {
											alert("Nickname can not be empty");
											return false;
										}
										var yob = $('#yob').val();
										if (yob == "") {
											alert("Year of Birth can not be empty");
											return false;
										}
										var reyob = /^\d{4}$/i;
										if (!reyob.test(yob)) {
											alert("Year of Birth format invalid");
											return false;
										}
										
										var address = $().val('#address');
										if (address == "") {
											alert("Address can not be empty");
											return false;
										}
										var creditcard = $('#creditcard').val();

										if (creditcard == "") {
											alert("Credit Card can not be empty");
											return false;
										}
										var re = /^\d{16}$/i;
										if (!re.test(creditcard)) {
											alert("credit card must be 16 digit number");
											return false;
										}

									});

				});
