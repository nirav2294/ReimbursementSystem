


async function logout() {
    let httpResponse = await fetch("http://localhost:8080/ExpenseReimbursementSystem/api/Logout");
    let logout = await httpResponse.json();
    console.log(user);
}