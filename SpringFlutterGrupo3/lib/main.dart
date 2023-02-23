import 'package:flutter/material.dart';
// import 'package:provider/provider.dart';
import 'package:trabajo_cop_flutter/screens/screens.dart';
// import 'package:trabajo_cop_flutter/services/orders_service.dart';
// import 'package:trabajo_cop_flutter/services/pedidos_service.dart';
// import 'package:trabajo_cop_flutter/services/producto_service.dart';
// import 'package:trabajo_cop_flutter/services/services.dart';

void main() => runApp(AppState());

class AppState extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // return MultiProvider(
    //   providers: [
    //     ChangeNotifierProvider(create: (_) => AuthService()),
    //     ChangeNotifierProvider(create: (_) => GetCiclos()),
    //     ChangeNotifierProvider(create: (_) => UsersListService()),
    //     ChangeNotifierProvider(create: (_) => GetArticulos()),
    //     ChangeNotifierProvider(create: (_) => GetPedidos()),
    //     ChangeNotifierProvider(create: (_) => ProductoService()),
    //     ChangeNotifierProvider(create: (_) => OrderService()),
    //   ],
    //   child: MyApp(),
    // );
    return MyApp();
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Material App',
      initialRoute: 'login',
      routes: {
        'login': (_) => LoginScreen(),
        'register': (_) => RegisterScreen(),
        // 'home': (_) => HomeScreen(),
        // 'admin': (_) => AdminScreen(),
        // 'catalogo': (_) => CatalogoScreen(),
        // 'pedidos': (_) => PedidosScreen(),
      },
      theme:
          ThemeData.light().copyWith(scaffoldBackgroundColor: Colors.grey[300]),
    );
  }
}
