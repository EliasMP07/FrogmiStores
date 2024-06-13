package com.example.frogmistores.presentation.storeList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.frogmistores.core.presentation.designsystem.CodeIcon
import com.example.frogmistores.core.presentation.designsystem.LocationStore
import com.example.frogmistores.core.presentation.designsystem.StoreIcon
import com.example.frogmistores.domain.model.Store


@Composable
fun ItemStore(
    modifier: Modifier = Modifier,
    store: Store,
    onStoreClick: () -> Unit
){
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(20.dp),
        shape = RoundedCornerShape(30.dp),
        onClick = onStoreClick
    ) {
        Box{
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .border(2.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(30.dp))
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                IconStore()
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ){
                    Text(text = store.name, style = MaterialTheme.typography.titleLarge)
                    FieldStore(icon = CodeIcon , text = store.code )
                    FieldStore(icon = LocationStore, text = store.fullAddress)
                }
                Spacer(modifier = Modifier
                    .weight(1f)
                    .padding(10.dp))
            }
        }
    }
}

@Composable
private fun IconStore(){
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.onBackground),
        contentAlignment = Alignment.Center
    ){
        Icon(
            imageVector = StoreIcon, contentDescription = "Icono de tienda",
            tint = MaterialTheme.colorScheme.background
        )
    }
}
@Composable
fun FieldStore(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(imageVector = icon, contentDescription = "Icono de campo")
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}

