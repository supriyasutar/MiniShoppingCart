# 🛒 Shopping Cart Mini Android App

## 📱 About the Project
This is a **mini Android application** built as part of a screening assignment.  
Due to limited time, the app focuses on **basic functionality and clear logic** with a **simple UI**.

The goal of this project is to demonstrate:
- Understanding of Android fundamentals
- Basic MVVM structure
- Correct cart and coupon business logic

---

## ✨ Features

### 🧺 Products
- Displays multiple products stored in memory
- Products have mixed tax slabs (5% / 18%)
- Some products are shown at discounted prices

### 🛍️ Cart
- Add items to cart
- View cart summary:
  - Subtotal
  - Tax total
  - Discount
  - Final payable amount

### 🎟️ Coupon Logic
- 20% discount coupon
- Minimum cart value: ₹1000
- Maximum discount: ₹300
- Coupon not applicable on already discounted items
- Apply button enabled only when eligible
- Button text changes to **APPLIED** after applying

### 🎉 Checkout
- Simple animation shown on checkout action

---

## 🧠 Architecture

- **Single Activity** architecture
- **MVVM (Model–View–ViewModel)**
- In-memory data storage (no database or API)
- Repository handles cart and coupon logic
- ViewModel exposes state to UI

---

## 🏗️ Tech Stack

- Kotlin
- XML layouts
- LiveData
- DataBinding
- RecyclerView


