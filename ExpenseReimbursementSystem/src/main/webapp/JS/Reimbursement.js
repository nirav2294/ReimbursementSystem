let managers;
let types;
async function myFunction() {

    let httpResponse = await fetch("http://ec2-3-17-161-23.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/api/getCurrentUser");
    if (httpResponse.status === 403) {
        window.location.href = "http://ec2-3-17-161-23.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/index.html";
    }
    let user = await httpResponse.json();
    console.log(user.employeeId);
    document.getElementById("employeeId").value = user.employeeId;
    document.getElementById("firstName").value = user.firstName;
    document.getElementById("lastName").value = user.lastName;

    let httpResponse2 = await fetch("http://ec2-3-17-161-23.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/api/getAllManagers");
    managers = await httpResponse2.json();
    console.log(managers)
    let selectData = document.getElementById("managerId");
    for (i = 0; i < managers.length; i++) {
        let option = document.createElement('option');
        option.innerHTML = managers[i].managerId + " | " + managers[i].firstName + " " + managers[i].lastName;
        option.value = managers[i].managerId;
        selectData.appendChild(option);
    }

    let httpResponse3 = await fetch("http://ec2-3-17-161-23.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/api/getReimbursementTypes");
    types = await httpResponse3.json();

    let selectType = document.getElementById("type");
    for (i = 0; i < types.length; i++) {
        let option = document.createElement('option');
        option.innerHTML = types[i].reimbursementType;
        option.value = i;
        selectType.appendChild(option);
    }
}



document.getElementById("reimbursementSubmit").addEventListener("click", createReimbursement);

async function createReimbursement() {

    let id = document.getElementById("managerId").value;
    let mid = managers[id - 1].managerId;
    let type = document.getElementById("type").value;
    let rtype = types[type].reimbursementType;
    let reimbursement = {
        employeeId: document.getElementById("employeeId").value,
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        managerId: mid,
        description: document.getElementById("description").value,
        expenseDate: document.getElementById("expenseDate").value,
        amount: document.getElementById("amount").value,
        type: rtype
    }

    let settings = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(reimbursement)
    }
    let httpResponse = await fetch("http://ec2-3-17-161-23.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/api/submitReimbursement" , settings);
    let r = await httpResponse.json();
    if(r != null){
        alert("Reimbursement Submitted \n" + "ReimbursementId = " + r.reimbursementId);
    }
    else{
        alert("Reimbursement submit unsuccessfull");
    }


}
