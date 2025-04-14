function addToCart(bookId) {
  if (confirm("장바구니에 도서를 추가합니까?") === true) {
    document.addForm.action="/BookMarket/cart/book/"+bookId;
    document.addForm.submit();
  }
}

function removeFromCart(bookId, cartId) {
  document.removeForm.action="/BookMarket/cart/book/"+bookId;
  document.removeForm.submit();
  setTimeout(document.location.reload, 10);
}

function clearCart(cartId) {
  if (confirm("모든 도서를 장바구니에서 삭제합니까?") === true) {
    document.clearForm.submit();
    setTimeout(document.location.reload, 10);
  }
}