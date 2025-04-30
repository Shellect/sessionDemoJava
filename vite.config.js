import {defineConfig} from 'vite';
import legacy from "@vitejs/plugin-legacy";

export default defineConfig({
    plugins: [legacy({
        targets: ['defaults', "not IE 11"]
    })],
    build: {
        outDir: 'src/main/resources/static/dist',
        assetsDir: 'assets',
        emptyOutDir: true,
        rollupOptions: {
            input: {
                main: 'src/main/resources/static/js/main.js',
                styles: 'src/main/resources/static/scss/styles.scss'
            },
            output: {
                entryFileNames: 'assets/[name].js',
                chunkFileNames: 'assets/[name].js',
                assetFileNames: 'assets/[name].[ext]'
            }
        }
    }
})