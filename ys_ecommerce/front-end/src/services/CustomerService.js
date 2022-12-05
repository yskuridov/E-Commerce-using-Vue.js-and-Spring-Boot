import axios from "axios"

const LOCALHOST = "http://localhost:8080/"

class CustomerService{
    async searchByName(name){
        return await axios.get(LOCALHOST + "products/name/" + name).then((response) => {return response.data});
    }

    async searchByVendor(vendorName){
        return await axios.get(LOCALHOST + "products/vendor/" + vendorName).then((response) => {return response.data});
    }

    async searchByDescription(description){
        return await axios.get(LOCALHOST + "products/description/" + description).then((response) => {return response.data});
    }

    async searchByPrice(price){
        return await axios.get(LOCALHOST + "products/price/" + price).then((response) => {return response.data});
    }

    async searchByFreeDelivery(){
        return await axios.get(LOCALHOST + "products/freedelivery").then((response) => {return response.data});
    }
    async getAllProducts(){
        return await axios.get(LOCALHOST + "products").then((response) => {return response.data});
    }
    async getVendorReviews(id){
        return await axios.get(LOCALHOST + "vendors" + "/id" + "/reviews" + id).then((response) => {return response.data});
    }
    async getVendorComments(id){
        return await axios.get(LOCALHOST + "vendors" + "/id" + "/comments" + id).then((response) => {return response.data});
    }
    async getCartItems(id){
        return await axios.get(LOCALHOST + "customers/" + id + "/cart").then((response) => {return response.data});
    }
    async addReview(review){
        return await axios.post(LOCALHOST + "vendors/reviews/add", review).then((response) => {return response.data});
    }
    async getOrders(id){
        return await axios.get(LOCALHOST + "customers/" + id + "/orders").then((response) => {return response.data});
    }

}
export default new CustomerService()