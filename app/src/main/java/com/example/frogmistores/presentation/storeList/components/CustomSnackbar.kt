package com.example.frogmistores.presentation.storeList.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomSnackbar(snackbarData: SnackbarData) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.Black,
        shadowElevation = 8.dp,
        modifier = Modifier.padding(5.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                modifier = Modifier.padding(horizontal = 10.dp),
                imageVector = Icons.Filled.Warning,
                contentDescription = "A",
                tint = Color.Yellow
            )
            Text(
                text = snackbarData.visuals.message,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.weight(1f))

        }
    }
}