async function myFunction() {

    let httpResponse = await fetch("http://localhost:8080/ExpenseReimbursementSystem/api/AllApprovedReimbursements");

    if (httpResponse.status === 403) {
        window.location.href = "http://localhost:8080/ExpenseReimbursementSystem/index.html";
    }
    let reimbursements = await httpResponse.json();
    console.log(reimbursements);
    let employeeRay = [];
    for (var i = 0; i < reimbursements.length; i++) {
        let obj = reimbursements[i];
        employeeRay.push(obj.employeeId);
    }
    let jsonString = JSON.stringify(employeeRay);
    let settings = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(jsonString)
    }
    httpResponse = await fetch("http://localhost:8080/ExpenseReimbursementSystem/api/EmployeesDetails", settings);
    let employees = await httpResponse.json();
    console.log(reimbursements);
    console.log(employees)
    let tableData = document.getElementById("tableData");
    for (i = 0; i < reimbursements.length; i++) {
        tableData.innerHTML = tableData.innerHTML + `<tr><th>${reimbursements[i].reimbursementId}</th><td>${reimbursements[i].managerId}</td>
            <td>${employees[i].firstName} ${employees[i].lastName}</td><td>${reimbursements[i].requestedDate}</td><td>${reimbursements[i].description}</td>
            <td>${reimbursements[i].expenseDate}</td><td>${reimbursements[i].type}</td><td>${reimbursements[i].amount}</td>
            <td>${reimbursements[i].status}</td></tr>`

    }


}