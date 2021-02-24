// App Imports
import { API_URL } from '../config/env'
import params from '../config/params'
import pages from './pages'
import user from './user'
import admin from './admin'


// Image
export const routeImageUser = `${API_URL}/${params.user.uploads.path}/`

// Combined routes
const routes = {
    ...pages,
    ...user,
    ...admin
}

export default routes