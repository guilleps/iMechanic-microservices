import {Component, OnInit} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthenticationSignUpDTORequest } from '../../../interfaces/AuthenticationSignUpDTORequest';
import { AuthService } from '../../../services/auth.service';
import { RouterLink } from '@angular/router';
import { LoadingRegisterUserComponent } from '../loading-register-user/loading-register-user.component';

@Component({
  selector: 'app-register-users',
  standalone: true,
  imports: [FormsModule, RouterLink, LoadingRegisterUserComponent],
  templateUrl: './register-users.component.html',
  styles: '',
})
export default class RegisterUsersComponent implements OnInit{
  passwordsMatch: boolean = true;
  showModalLoading = false;
  showModal = false;
  showModal2 = false;
  confirmarContrasenia: String = '';
  showMSGdi = false;
  showMSGci = false;
  showMSGminPass = false;
  showMSGminNm = false;
  showMSGminAdr = false;
  isEmailValid = true;
  showMSGValidEmail = true;

  signUpDTORequest: AuthenticationSignUpDTORequest = {
    email: '',
    password: '',
    name: '',
    phone: '',
    address: '',
    role: '',
  };

  showPassword: boolean = false;
  showPasswordConfirmation: boolean = false;
  showDescripcion: boolean = false;
  rol: string = 'CUSTOMER';

  constructor(private authService: AuthService) {}

  ngOnInit() {
    console.log("v1.1.5 corriendo OK")
  }

  openModal(): void {
    this.showModal = true;
  }

  openModalLoading(): void {
    this.showModalLoading = true;
  }

  closeModalLoading(): void {
    this.showModalLoading = false;
  }

  openModal2(): void {
    this.showModal2 = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  closeModal2(): void {
    this.showModal2 = false;
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  togglePasswordConfirmation(): void {
    this.showPasswordConfirmation = !this.showPasswordConfirmation;
  }

  toogleRol(): void {
    this.showDescripcion = !this.showDescripcion;
    this.rol = this.showDescripcion ? 'WORKSHOP' : 'CUSTOMER';
    // console.log('Estado de showDescripcion:', this.showDescripcion);
    // console.log('Rol:', this.rol);
  }

  validateEmail(email: string): boolean {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailPattern.test(email);
  }

  onSubmit() {
    if (this.checkIncompleteData()) {
      this.showMSGci = false;
      this.showMSGdi = true;
      this.showMSGminPass = false;
      this.showMSGValidEmail = true;
      return; // Detener la función si hay algún dato incompleto
    }

    // Verificar si las contraseñas coinciden

    if (this.signUpDTORequest.password !== this.confirmarContrasenia) {
      this.passwordsMatch = false;
      // console.log('Contraseñas iguales:', this.passwordsMatch);
      this.showMSGdi = false;
      this.showMSGci = true;
      this.showMSGminPass = false;
      this.showMSGValidEmail = true;
      return; // Detener la función si las contraseñas no coinciden
    }

    // console.log('Contraseñas iguales:', this.passwordsMatch);

    this.isEmailValid = this.validateEmail(this.signUpDTORequest.email);

    if (!this.isEmailValid) {
      this.showMSGValidEmail = false;
      this.showMSGdi = false;
      this.showMSGci = false;
      this.showMSGminPass = false;
      this.showMSGminAdr = false;
      this.showMSGminNm = false;
      return;
    }

    if (this.signUpDTORequest.password.length < 8) {
      this.showMSGdi = false;
      this.showMSGci = false;
      this.showMSGminPass = true;
      this.showMSGValidEmail = true;
      return;
    }

    if (this.signUpDTORequest.name.length < 5) {
      this.showMSGminNm = true;
      this.showMSGminAdr = false;
      this.showMSGdi = false;
      this.showMSGci = false;
      this.showMSGminPass = false;
      this.showMSGValidEmail = true;
      return;
    }

    if (this.signUpDTORequest.address.length < 5) {
      this.showMSGminAdr = true;
      this.showMSGminNm = false;
      this.showMSGdi = false;
      this.showMSGci = false;
      this.showMSGminPass = false;
      this.showMSGValidEmail = true;
      return;
    }

    this.showMSGValidEmail = true;
    this.showMSGdi = false;
    this.showMSGci = false;
    this.showMSGminPass = false;
    this.showMSGminAdr = false;
    this.showMSGminNm = false;
    // console.log('Contraseñas iguales:', this.passwordsMatch);
    this.signUpDTORequest.role = this.rol;
    this.openModalLoading();
    this.authService.signUp(this.signUpDTORequest).subscribe(
      (response) => {
        // añadir pantalla de carga hasta que llegue correo de verificacion enviado por favor confirme su cuenta
        console.log('Registro exitoso:', response);
        this.closeModalLoading();
        this.openModal();
      },
      (error) => {
        console.error('Error al registrar:', error);
        this.closeModalLoading();
        // console.log('se encontro un error');
      }
    );
  }

  onKeyPress(event: any) {
    // Obtener el código de la tecla presionada
    const charCode = event.which ? event.which : event.keyCode;
    // Permitir solo números (del 0 al 9) y teclas de control como borrar y retroceso
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      event.preventDefault(); // Cancelar el evento si no es un número
    }
  }

  onInput(event: any) {
    const input = event.target;
    // Si el valor no comienza con '9', agregar '9' al principio
    if (!input.value.startsWith('9')) {
      input.value = '9' + input.value.replace(/[^0-9]/g, '');
    } else {
      input.value = '9' + input.value.slice(1).replace(/[^0-9]/g, '');
    }

    // Limitar el número de caracteres a 9
    if (input.value.length > 9) {
      input.value = input.value.slice(0, 9);
    }
  }

  checkIncompleteData(): boolean {
    if (
      !this.signUpDTORequest.email ||
      !this.confirmarContrasenia ||
      !this.signUpDTORequest.password ||
      !this.signUpDTORequest.name ||
      !this.signUpDTORequest.address ||
      !this.signUpDTORequest.phone
    ) {
      return true; // Hay datos incompletos
    }
    return false; // No hay datos incompletos
  }
}
