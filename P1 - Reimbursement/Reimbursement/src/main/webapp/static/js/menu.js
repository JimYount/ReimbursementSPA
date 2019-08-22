var menuString = `

            <div class="vertical-menu">
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <a onclick="showSubmissions()">My Reimbursements</a>
                <a onclick="showSubmitForm()">Submit a Claim</a>
                <a onclick="manageSubmissions()">Manage Claims</a>
            </div>
            <div id="interactive-menu">
            </div>
        `;

function showMenu() {
    document.getElementById("mainContent").innerHTML = menuString;
}

function hideMenu(){
    document.getElementById("mainContent").innerHTML = ``;
}