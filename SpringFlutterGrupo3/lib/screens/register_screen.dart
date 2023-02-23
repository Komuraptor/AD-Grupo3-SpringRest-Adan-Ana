import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:trabajo_cop_flutter/providers/login_form_provider.dart';
import 'package:trabajo_cop_flutter/services/services.dart';
import 'package:trabajo_cop_flutter/widgets/widgets.dart';
import '../ui/input_decorations.dart';

class RegisterScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: AuthBackground(
            child: SingleChildScrollView(
      child: Column(
        children: [
          SizedBox(height: 205),
          CardContainer(
              child: Column(
            children: [
              SizedBox(height: 3),
              Text('Crear cuenta',
                  style: Theme.of(context).textTheme.headline4),
              SizedBox(height: 9),
              ChangeNotifierProvider(
                  create: (_) => LoginFormProvider(), child: _RegisterForm()),
            ],
          )),
          SizedBox(height: 25),
          TextButton(
            onPressed: () => Navigator.pushReplacementNamed(context, 'login'),
            style: ButtonStyle(
                overlayColor:
                    MaterialStateProperty.all(Colors.indigo.withOpacity(0.1)),
                shape: MaterialStateProperty.all(StadiumBorder())),
            child: Text('¿Deseas iniciar sesion?',
                style: TextStyle(fontSize: 18, color: Colors.black87)),
          ),
          SizedBox(height: 50),
        ],
      ),
    )));
  }
}

class _RegisterForm extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final loginForm = Provider.of<LoginFormProvider>(context);
    //const List<Ciclos> listCiclos = authService.getCicles();

    return Form(
        key: loginForm.formKey,
        autovalidateMode: AutovalidateMode.onUserInteraction,
        child: Column(
          children: [
            TextFormField(
              autocorrect: false,
              keyboardType: TextInputType.emailAddress,
              decoration: InputDecorations.authInputDecoration(
                  hintText: 'Usuario..',
                  labelText: 'Nombre de usuario',
                  prefixIcon: Icons.account_circle_outlined),
              onChanged: (value) => loginForm.username = value,
              validator: (value) {},
            ),
            SizedBox(height: 4),
            TextFormField(
              autocorrect: false,
              obscureText: true,
              keyboardType: TextInputType.emailAddress,
              decoration: InputDecorations.authInputDecoration(
                  hintText: '*******',
                  labelText: 'Contraseña',
                  prefixIcon: Icons.lock_outline),
              onChanged: (value) => loginForm.password = value,
              validator: (value) {
                return (value != null && value.length >= 6)
                    ? null
                    : 'La contraseña tiene que tener mas de 6 caracteres';
              },
            ),
            SizedBox(height: 4),
            TextFormField(
              autocorrect: false,
              obscureText: true,
              keyboardType: TextInputType.emailAddress,
              decoration: InputDecorations.authInputDecoration(
                  hintText: '*******',
                  labelText: 'Repite la contraseña',
                  prefixIcon: Icons.lock_outline),
              onChanged: (value) => loginForm.cpassword = value,
              validator: (value) {
                return (value != null && value == loginForm.password)
                    ? null
                    : 'Tiene que ser la misma contraseña';
              },
            ),
            SizedBox(height: 10),
            MaterialButton(
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(10)),
              disabledColor: Colors.grey,
              elevation: 0,
              color: Colors.deepPurple,
              child: Container(
                  padding: EdgeInsets.symmetric(horizontal: 60, vertical: 15),
                  child: Text(
                    loginForm.isLoading ? 'Espere' : 'Ingresar',
                    style: TextStyle(color: Colors.white),
                  )),
              onPressed: loginForm.isLoading
                  ? null
                  : () async {
                      FocusScope.of(context).unfocus();
                      final authService =
                          Provider.of<AuthService>(context, listen: false);

                      if (!loginForm.isValidForm()) return;

                      loginForm.isLoading = true;

                      final String? errorMessage = await authService.register(
                        loginForm.username,
                        loginForm.password,
                        loginForm.cpassword,
                      );

                      if (errorMessage == null) {
                        //Navigator.pushReplacementNamed(context, 'home');

                      } else {
                        Navigator.pushReplacementNamed(context, 'home');
                        loginForm.isLoading = false;
                      }
                    },
            )
          ],
        ));
  }
}
