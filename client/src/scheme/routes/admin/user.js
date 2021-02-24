// App Imports
import params from '../../config/params.json'
import UserList from '../../../modules/core/admin/user/List'

// Admin user routes
export default {
    adminUserList: {
        path: '/admin/users',
        component: UserList,
        auth: true,
        role: params.user.roles.admin.key
    }
}