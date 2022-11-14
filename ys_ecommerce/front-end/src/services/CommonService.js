import axios from "axios"

const LOCALHOST = "http://localhost:8080/"

class CommonService{
    async createAccount(username, password, firstName, lastName, address, accountType){
        return await axios.get(LOCALHOST + "register", {username: username, password: password, name: firstName, lastName: lastName, address: address, accountType: accountType})
        .then((response) => {return response.data});
    }

    async login(username, password){
        return await axios.post(LOCALHOST + "login", {username: username, password: password}).then((response) => {return response.data});
    }
}

export default new CommonService()