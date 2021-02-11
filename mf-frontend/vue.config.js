module.exports = {
  devServer: {
    port: 8090,
    proxy: {
      "/": {
        target: "http://localhost:8080", //Gateway Address
        ws: true,
        changeOrigin: true
      }
    }
  },
  transpileDependencies: ["vuetify"],
  assetsDir: "assets"
};
