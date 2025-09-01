import { cartAPI } from './axios'

export interface Product {
    productId: number
    title: string
    quantity: number
    price: number
    category: string
}

export interface CartDTO {
    id: number
    userId: number
    createdAt: string
    cartItems: Product[]
}

export const CartAPI = {
    async getCart(cartId: number): Promise<CartDTO> {
        const response = await cartAPI.get(`/carts/${cartId}`)
        return response.data
    },

    async removeProduct(productId: number): Promise<void> {
        await cartAPI.delete(`/cart-items/${productId}`)
    }

}