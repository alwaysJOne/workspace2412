import axios from "axios";
import { create } from "zustand";

const usePostStore = create((set) => ({
    posts: [],
    loading: false,
    error: null,
    
    getPosts: async () => {
        set({ loading: true, error: null });

        try{
            const response = await axios.get("https://jsonplaceholder.typicode.com/posts");
        
            set({posts: response.data, loading: false});
        } catch(error){
            set({ loading: false, error: error.message });
        }
    },
    deletePost: async (id) => {
        set({ loading: true, error: null });

        try{
            await axios.delete(`https://jsonplaceholder.typicode.com/posts/${id}`);
        
            set((state) => ({
                posts: state.posts.filter(post => post.id !== id),
                loading: false,
            }));
        } catch(error){
            set({ loading: false, error: error.message });
        }
    }
}));

export default usePostStore;