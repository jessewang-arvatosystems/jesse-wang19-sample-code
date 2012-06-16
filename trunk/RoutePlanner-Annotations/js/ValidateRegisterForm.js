function ValidateRegisterForm()
{
    var first = document.RegisterForm.first_name;
    var last = document.RegisterForm.last_name;
    var username1 = document.RegisterForm.username;
    var pw = document.RegisterForm.password;



    if (first.value == "")
    {
        window.alert("Please enter your first name.");
        first.focus();
        return false;
    }
     if (last.value == "")
    {
        window.alert("Please enter your last name.");
        last.focus();
        return false;
    }
    
    if (username1.value == "")
    {
        window.alert("Please enter a username!");
        username1.focus();
        return false;
    }

    if ( pw.value == "")
    {
        window.alert("Please enter a password.");
        pw.focus();
        return false;
    }

    return true;
}