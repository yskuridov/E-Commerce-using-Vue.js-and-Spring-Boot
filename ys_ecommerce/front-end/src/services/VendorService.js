import axios from "axios"

const LOCALHOST = "http://localhost:8080/"

class VendorService{
    async getVendorInventory(id){
        return await axios.get(LOCALHOST + "vendors/" + id + "/inventory").then((response) => {return response.data});
    }

    async getProductById(id){
        return await axios.get(LOCALHOST + "vendors/" + id + "/inventory").then((response) => {return response.data});
    }

    async updateProduct(p){
        return await axios.post(LOCALHOST + "products/update", 
        {id: p.id, name: p.name, description: p.description, price: p.price, deliveryFee: p.deliveryFee, imageUrl: p.imageUrl, stock: p.stock, soldBy: p.soldBy, vendorUsername: p.vendorUsername})
        .then((response) => {return response.data});
    }
}
export default new VendorService()