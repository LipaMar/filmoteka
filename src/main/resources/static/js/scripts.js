function search() {
    let movie = document.getElementById("searchBar").value;
    window.location = findPath + movie;
}

$(document).ready( function() {
    let searchBar = document.getElementById("searchBar");
    let searchBtn = document.getElementById("searchBtn");
    searchBtn.addEventListener("click",search);
    searchBar.addEventListener("keyup", function (event) {
        if (event.code === 'Enter') {
            event.preventDefault();
            searchBtn.click();
        }
    });
});
