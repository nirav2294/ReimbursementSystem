<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- <link rel="icon" href="../../../../favicon.ico"> -->

    <style></style>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="JS/Footer&Navbar.js"></script>

    <!-- Inline CSS based on choices in "Settings" tab -->
    <style>
        .bootstrap-iso .formden_header h2,
        .bootstrap-iso .formden_header p,
        .bootstrap-iso form {
            font-family: Arial, Helvetica, sans-serif;
            color: black
        }

        .bootstrap-iso form button,
        .bootstrap-iso form button:hover {
            color: white !important;
        }

        .asteriskField {
            color: red;
        }
    </style>

</head>
<link href="https://unpkg.com/browse/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet"/>
<body onload="myFunction()">
    <div id="nav-placeholder"></div>

    <div class="container">
        <div class="card"> </div>

        <div class="jumbotron" style="padding-top: 0px;">


            <div class="container"></div>
            <p></p>
            <div class="card-body"
                style="margin-bottom: auto; margin-top: auto; margin-right: auto; padding-top: 20px; padding-bottom: 0px; font-weight: 500; display: block; top: auto; bottom: auto; float: none; line-height: 24px; width: 642px;"
                id="title">
                <h6 class="card-title"
                    style="float: none; display: block; top: auto; text-align: left; text-decoration-line: none;"
                    contenteditable="false" spellcheckker="false">Expense Reimbursement</h6>
            </div>
            <hr style="font-weight: 900; line-height: 20px;">

            <form method="post" action="uploadServlet" enctype="multipart/form-data">
                <div class="form-group row">
                    <label for="employeeId" class="col-4 col-form-label">Employee ID</label>
                    <div class="col-8">
                        <input id="employeeId" name="employeeId" placeholder="empl id" type="number"
                            required="required " class="form-control" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-4 col-form-label" for="firstName">First Name</label>
                    <div class="col-8">
                        <input id="firstName" name="firstName" placeholder="first name" type="text" required="required"
                            class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="lastName" class="col-4 col-form-label">Last Name</label>
                    <div class="col-8">
                        <input id="lastName" name="lastName" placeholder="last name" type="text" class="form-control"
                            required="required">
                    </div>
                </div>

                <div class="form-group row" >

                    <label for="exampleFormControlFile1" class="col-4 col-form-label">Please upload any related
                        files</label>
                    <div class="col-8">
                        <input name="fileUpload"type="file" class="form-control-file" id="fileUpload" required="required"
                            accept="image/*, .txt">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="offset-4 col-8">
                        <input type="submit" id="reimbursementSubmit" name="submit" id="submitButton" class="btn btn-primary"
                            value="Submit">
                    </div>
                </div>
            </form>
        </div>
        <div class="row marketing">
        </div>

        <div id="footer-placeholder"></div>

    </div> <!-- /container -->


</body>
<script>

     async function myFunction() {

        let httpResponse = await fetch("http://ec2-3-133-103-178.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/api/getCurrentUser");
        if (httpResponse.status === 403) {
            window.location.href = "http://ec2-3-133-103-178.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/index.html";
        }
        let user = await httpResponse.json();
        console.log(user.employeeId);
        document.getElementById("employeeId").value = user.employeeId;
        document.getElementById("firstName").value = user.firstName;
        document.getElementById("lastName").value = user.lastName;

        let httpResponse2 = await fetch("http://ec2-3-133-103-178.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/api/getAllManagers");
        if (httpResponse.status === 403) {
            window.location.href = "http://ec2-3-133-103-178.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/index.html";
        }
        let managers = await httpResponse2.json();
        console.log(managers)
        let selectData = document.getElementById("managerId");
        for (i = 0; i < managers.length; i++) {
            var option = document.createElement('option');
            option.innerHTML = managers[i].managerId + " | " + managers[i].firstName + " " + managers[i].lastName;
            option.value = managers[i].managerId;
            selectData.appendChild(option);
        }

        document.getElementById("employeeId").value = user.employeeId;
        document.getElementById("employeeId").value = user.employeeId;
        document.getElementById("employeeId").value = user.employeeId;
    }



    document.getElementById("reimbursementSubmit").addEventListener("click", createReimbursement);

    function createReimbursement() {

        let reimbursement = {

        }
    } 

</script>

</html>
