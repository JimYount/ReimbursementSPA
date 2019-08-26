var menuString = `
    <table style="width: 100%; height: 100%; margin: auto">
        <tr>
            <td class="vertical-menu" valign="top">
                <a onclick="showSubmissions()">My Reimbursements</a>
                <a onclick="showSubmitForm()">Submit a Claim</a>
                <a onclick="manageSubmissions()">Manage Claims</a>
            </td>
            <td id="interactive-menu" valign="top">
                
            </td>
        </tr>
    </table>
        `;

function showMenu() {
    document.getElementById("mainContent").innerHTML = menuString;
}

function hideMenu(){
    document.getElementById("mainContent").innerHTML = ``;
}