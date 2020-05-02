document.getElementById("eLogin").addEventListener("click", employeeLogin);
document.getElementById("mLogin").addEventListener("click", managerLogin);
async function employeeLogin() {



    let login = {
        userName: document.getElementById("eUsername").value,
        password: document.getElementById("ePassword").value
    }

    if (login.userName === "" || login.password === "") {
        document.getElementById("alertmessage").innerHTML = `<i class="material-icons" style="font-size:16px" >error</i>` + " Please fill out all fields";
    }

    console.log(login);
    let settings = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(login)
    }

    let httpResponse = await fetch("http://ec2-3-133-103-178.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/api/LoginPage?username=" + login.userName, settings);
    let user = await httpResponse.json();
    console.log(user)

    if (user.employeeId != 0) {
        window.location.href = "http://ec2-3-133-103-178.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/EmployeeHome.html";
    }
    else {
        document.getElementById("alertmessage").innerHTML = `<i class="material-icons" style="font-size:16px" >error</i>` + " Incorrect username or password";
        //window.location.href = "http://ec2-3-17-161-23.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/index.html";
    }
}

async function managerLogin() {

    let login = {
        userName: document.getElementById("mUsername").value,
        password: document.getElementById("mPassword").value
    }

    if (login.userName === "" || login.password === "") {
        console.log("error");
        document.getElementById("alertmessagem").innerHTML = `<i class="material-icons" style="font-size:16px" >error</i>` + " Please fill out all fields";
    }

    console.log(login);
    let settings = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(login)
    }

    let httpResponse = await fetch("http://ec2-3-133-103-178.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/api/ManagerLoginPage?username=" + login.userName, settings);
    let user = await httpResponse.json();
    console.log(user.managerId)
    if (user.managerId != 0) {
        window.location.href = "http://ec2-3-133-103-178.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/ManagerHome.html";
    }
    else {
        window.location.href = "ec2-3-133-103-178.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/index.html";
    }
}
