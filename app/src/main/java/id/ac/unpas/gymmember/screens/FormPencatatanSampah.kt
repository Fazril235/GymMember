package id.ac.unpas.gymmember.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.gymmember.model.GymMember
import id.ac.unpas.gymmember.persistences.GymMemberDao
import id.ac.unpas.gymmember.ui.theme.Purple700
import id.ac.unpas.gymmember.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanSampah(gymMemberDao: GymMemberDao) {

    val name = remember { mutableStateOf(TextFieldValue("")) }
    val phonenumber = remember { mutableStateOf(TextFieldValue("")) }
    val registrationdate = remember { mutableStateOf(TextFieldValue("")) }
    val expireddate = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {

        OutlinedTextField(
            label = { Text(text = "Name") },
            value = name.value,
            onValueChange = {
                name.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Phone Number") },
            value = phonenumber.value,
            onValueChange = {
                phonenumber.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "5") }
        )
        OutlinedTextField(
            label = { Text(text = "Registration Date") },
            value = registrationdate.value,
            onValueChange = {
                registrationdate.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Expired Date") },
            value = expireddate.value,
            onValueChange = {
                expireddate.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = GymMember(id, name.value.text,
                    phonenumber.value.text, registrationdate.value.text, expireddate.value.text)
                scope.launch {
                    gymMemberDao.insertAll(item)
                }

                name.value = TextFieldValue("")
                phonenumber.value = TextFieldValue("")
                registrationdate.value = TextFieldValue("")
                expireddate.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Save",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                name.value = TextFieldValue("")
                phonenumber.value = TextFieldValue("")
                registrationdate.value = TextFieldValue("")
                expireddate.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}