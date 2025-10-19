# iMechanic - Frontend

Angular-based frontend for the iMechanic platform, a comprehensive automotive service management system that connects vehicle owners with mechanics and service providers.

### Prerequisites
- Node.js 18+ (LTS recommended)
- Angular CLI 17

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/guilleps/iMechanic-microservices.git
   cd iMechanic-microservices/front
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Start development server**
   ```bash
   ng serve
   ```
   The application will be available at `http://localhost:4200`

## 🏗️ Project Structure

```
└── 📁front
    └── 📁.husky
        ├── commit-msg
        ├── pre-push
    └── 📁.vscode
        ├── extensions.json
        ├── launch.json
        ├── tasks.json
    └── 📁src
        └── 📁app
            └── 📁client-view
            └── 📁employee-view
            └── 📁footer
            └── 📁guards
            └── 📁interceptor
            └── 📁interfaces
            └── 📁layout-pages
            └── 📁mechanic-view
            └── 📁order-progress
            └── 📁services
            ├── app.component.css
            ├── app.component.html
            ├── app.component.spec.ts
            ├── app.component.ts
            ├── app.config.ts
            ├── app.routes.ts
        └── 📁assets
        └── 📁environments
        ├── index.html
        ├── main.ts
        ├── styles.css
    ├── angular.json
    ├── package.json
    ├── README.md
```

## 🛠️ Development

### Available Scripts

- `ng serve` - Start development server with hot-reload
- `ng build` - Build for production
- `ng test` - Run unit tests
- `ng e2e` - Run end-to-end tests
- `ng lint` - Run linting
- `ng generate component component-name` - Generate new component

### Code Style
- Follows [Angular Style Guide](https://v17.angular.io/guide/styleguide)
- Uses Prettier for code formatting
- ESLint for TypeScript/JavaScript linting
- Husky pre-commit hooks for code quality

## 🔒 Authentication & Authorization

The application implements role-based access control with:
- Client view for customers
- Mechanic view for service providers
- Employee view for administrative staff

## 🌐 API Integration

API calls are made through Angular's HttpClient service with:
- HTTP interceptors for authentication
- Centralized error handling
- Type-safe API services

## 🚀 Deployment

For production deployment:

```bash
# Build for production
ng build --configuration=production

# The build artifacts will be stored in the `dist/` directory
```

