
async function logout() {
    let httpResponse = await fetch("http://ec2-3-133-103-178.us-east-2.compute.amazonaws.com:8080/ExpenseReimbursementSystem/api/Logout");
    let logout = await httpResponse.json();
    console.log(user);
}
