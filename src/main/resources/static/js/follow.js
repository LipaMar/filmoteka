let token;
let header
$(document).ready(function(){
    let btn = document.getElementById("follow-btn");
     token = $("meta[name='_csrf']").attr("content");
     header = $("meta[name='_csrf_header']").attr("content");
    if(btn!==null)
    {
        btn.addEventListener("click",follow);
        isFollowing();
    }
});


function changeFollowIcon(xhttp) {
    return function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            if (xhttp.response === "true") {
                document.getElementById("follow-btn").className = "btn btn-success";
                document.getElementById("follow-icon").className = "fas fa-heart";
                document.getElementById("follow-text").innerText = "Obserwujesz";
            } else if (xhttp.response === "false") {
                document.getElementById("follow-btn").className = "btn btn-primary";
                document.getElementById("follow-icon").className = "far fa-heart";
                document.getElementById("follow-text").innerText = "Obserwuj";
            }
        }
    }
}
function isFollowing() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = changeFollowIcon(xhttp);
    xhttp.open("GET", path, true);
    setRequiredHeaders(xhttp);
    xhttp.send();
}

function setRequiredHeaders(xhttp) {
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.setRequestHeader(header, token);
}

function follow() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = changeFollowIcon(xhttp);
    xhttp.open("POST", path, true);
    setRequiredHeaders(xhttp);
    xhttp.send();
}