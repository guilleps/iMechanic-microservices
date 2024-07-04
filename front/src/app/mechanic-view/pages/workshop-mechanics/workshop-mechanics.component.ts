import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgClass, NgForOf, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { MechanicService } from '../../../services/mechanic.service';
import { MechanicDTORequest } from '../../../interfaces/MechanicDTORequest';
import { TallerServiceService } from '../../../services/taller-service.service';
import { Subscription } from 'rxjs';
import { LoaderComponent } from '../loader/loader.component';
import { OperationDTOResponse } from '../../../interfaces/ServicioDTO';
import { MechanicDetailsDTOResponse } from '../../../interfaces/MechanicDetailsDTOResponse';

@Component({
  selector: 'app-workshop-mechanics',
  standalone: true,
  imports: [
    NgForOf,
    FormsModule,
    NgxPaginationModule,
    NgIf,
    LoaderComponent,
    NgClass,
  ],
  templateUrl: './workshop-mechanics.component.html',
  styles: '',
})
export default class WorkshopMechanicsComponent implements OnInit, OnDestroy {
  p: number = 1;

  mechanics: MechanicDetailsDTOResponse[] = [];
  suscription: Subscription | undefined;
  newMechanic: MechanicDTORequest = {
    name: '',
    email: '',
    password: '',
    phone: '',
    operationsIds: [],
  };
  servicios: OperationDTOResponse[] = [];
  selectedServicesMechanic: number[] = [];

  showModal = false;
  showModal2 = false;
  showModal3 = false;
  showPassword: boolean = false;
  showServices: boolean = false;
  showModalEmpty: boolean = false;
  showModalVemail: boolean = false;
  showModalminName: boolean = false;
  showModalminPass: boolean = false;

  isEmailValid = true;

  constructor(
    private mechanicService: MechanicService,
    private tallerService: TallerServiceService
  ) {
    this.newMechanic.operationsIds = [];
  }

  openModalEmpty(): void {
    this.showModalEmpty = true;
  }

  closeModalEmpty(): void {
    this.showModalEmpty = false;
  }

  openModalminPass(): void {
    this.showModalminPass = true;
  }

  closeModalminPass(): void {
    this.showModalminPass = false;
  }

  openModalminName(): void {
    this.showModalminName = true;
  }

  closeModalminName(): void {
    this.showModalminName = false;
  }

  openModalVemail(): void {
    this.showModalVemail = true;
  }

  closeModalVemail(): void {
    this.showModalVemail = false;
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  ngOnInit(): void {
    this.mechanicService.getAllMechanics().subscribe((mecanicos) => {
      this.mechanics = mecanicos;
    });

    this.suscription = this.mechanicService.refresh$.subscribe(() => {
      this.mechanicService.getAllMechanics().subscribe((mecanicos) => {
        this.mechanics = mecanicos; // Aquí asigna los datos actualizados a la variable vehicles
      });
    });
  }

  ngOnDestroy(): void {
    this.suscription?.unsubscribe();
    // console.log('obserbable morido');
  }

  validateEmail(email: string): boolean {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailPattern.test(email);
  }

  createMechanic(): void {
    // console.log('Si');

    if (
      !this.newMechanic.name ||
      !this.newMechanic.email ||
      !this.newMechanic.password ||
      !this.newMechanic.phone
    ) {
      this.openModalEmpty(); // Mostrar modal de error si algún campo está vacío
      this.closeModalVemail();
      this.closeModalminPass();
      this.closeModalminName();
      return;
    }

    this.isEmailValid = this.validateEmail(this.newMechanic.email);

    if (!this.isEmailValid) {
      this.openModalVemail();
      this.closeModalEmpty();
      this.closeModalminPass();
      this.closeModalminName();
      return;
    }

    if (this.newMechanic.password.length < 8) {
      this.openModalminPass();
      this.closeModalminName();
      this.closeModalVemail();
      this.closeModalEmpty();
      return;
    }

    if (this.newMechanic.name.length < 5) {
      this.openModalminName();
      this.closeModalminPass();
      this.closeModalVemail();
      this.closeModalEmpty();
      return;
    }

    this.showModal = false;
    this.showModal2 = true;
    this.showModalEmpty = false;
    this.showModalVemail = false;
    this.showModalminName = false;
    this.showModalminPass = false;
    this.mechanicService.createMechanic(this.newMechanic).subscribe(
      (response) => {
        // console.log('Mechanic created successfully', response);
        this.showModal2 = false;
        this.showModal3 = true;
        // Restablece los valores de los campos de registro
        this.newMechanic.name = '';
        this.newMechanic.email = '';
        this.newMechanic.password = '';
        this.newMechanic.phone = '';
        this.newMechanic.operationsIds = [];
      },
      (error) => {
        console.error('Error creating mechanic: ', error);
        this.showModal = false;
        this.showModal2 = false;
        // console.log('Se detecto un error');
      }
    );
    this.showModal2 = true;
  }

  toggleServicios(): void {
    this.showServices = !this.showServices;
  }

  openModal(): void {
    this.showModal = true;
    this.tallerService.getAllServices().subscribe((servicios) => {
      this.servicios = servicios;
    });
  }

  closeModal(): void {
    this.showModal = false;
    this.newMechanic.name = '';
    this.newMechanic.email = '';
    this.newMechanic.password = '';
    this.newMechanic.phone = '';
    this.newMechanic.operationsIds = [];
  }

  toggleServicio(servicioId: number): void {
    if (this.newMechanic.operationsIds.includes(servicioId)) {
      // console.log(`Removing servicioId ${servicioId}`);
      this.newMechanic.operationsIds = this.newMechanic.operationsIds.filter(
        (id) => id !== servicioId
      );
    } else {
      // console.log('servicioId', servicioId);
      this.newMechanic.operationsIds.push(servicioId);
    }
  }

  selectedMechanic: MechanicDetailsDTOResponse | null = null;

  toggleServicios2(mechanic: MechanicDetailsDTOResponse): void {
    this.showServices = !this.showServices;
    this.selectedMechanic = mechanic;
  }

  closeModal3(): void {
    this.showModal3 = false;
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
}
