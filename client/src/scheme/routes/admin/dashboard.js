// App Imports
import params from '../../../scheme/config/params'
import Dashboard from '../../../modules/core/admin/dashboard/Dashboard'

// Admin dashboard routes
export default {
    adminDashboard: {
        path: '/admin/dashboard',
        component: Dashboard,
        auth: true,
        role: params.user.roles.admin.key
    },
}