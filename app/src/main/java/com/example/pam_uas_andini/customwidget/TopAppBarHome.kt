package com.example.pam_uas_andini.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pam_uas_andini.R

@Preview(showBackground = true)
@Composable
fun TopAppBarHome(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp)

            .background(color = colorResource(id = R.color.primary), shape = RoundedCornerShape(bottomEnd = 60.dp)
            )
    )
    {
        Row (
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .size(30.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "Nestoria",
                    modifier = Modifier.padding(end = 30.dp),
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Smart, Cozy, Elegant",
                    color = Color.White,
                    fontSize = 22.sp
                )
            }
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(40.dp)),
                contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = R.drawable.lbaru),
                    contentDescription = "",
                    Modifier.size(55.dp)
                )
            }
        }
    }
}