import axios from "axios"

const LOCALHOST = "http://localhost:8080/"

class VendorService{
    async getVendorInventory(id){
        return await axios.get(LOCALHOST + "vendors/" + id + "/inventory").then((response) => {return response.data});
    }
}
export default new VendorService()