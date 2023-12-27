const jsonServer = require("json-server");
const server = jsonServer.create();
const router = jsonServer.router("db.json");
const middlewares = jsonServer.defaults();

server.use(jsonServer.bodyParser);
server.use(middlewares);
const PORT = process.env.PORT || 5000
// Custom middleware to access POST methids.
// Can be customized for other HTTP method as well.
server.use((req, res, next) => {
  console.log("POST request listener");
  const body = req.body;
  console.log(body);
  next();

});
router.render = (req, res) => {
    console.log("foo")
    res.send({
        type:0,
        code:"0",
        msg:"操作成功",
        data:"r5G00DNU"
    }
        
        )
  }
  // Add this before server.use(router)
server.use(jsonServer.rewriter({
    '/xpublish-web/service/rs/v1/operationService/*': '/$1',
   
  }))
server.use(router);

server.listen(PORT, () => {
  console.log("JSON Server is running");
  console.log(`Run Auth API Server on https://localhost:${PORT}/`)
});