import { api } from './axios'

export interface Product {
    id: number
    title: string
    price: number
    category: string
}

export interface AddToCartRequest {
    userId: number
    productId: number
}

export const ProductAPI = {
    async getAll(): Promise<Product[]> {
        const response = await api.get('/products')
        return response.data
    },

    async addToCart(request: AddToCartRequest): Promise<void> {
        const response = await api.post('/products/add-to-cart', request)
        if (!response.status || response.status >= 400) {
            throw new Error('Failed to add product to cart')
        }
    },
}
