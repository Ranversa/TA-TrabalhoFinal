
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PecasMaoDeObraDAO;
import br.edu.ifsul.dao.OrcamentoDAO;
import br.edu.ifsul.dao.VeiculoDAO;
import br.edu.ifsul.modelo.ItemOrcamento;
import br.edu.ifsul.modelo.PecasMaoDeObra;
import br.edu.ifsul.modelo.Orcamento;
import br.edu.ifsul.modelo.Veiculo;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ROBSON
 */
@Named(value = "controleOrcamento")
@SessionScoped
public class ControleOrcamento implements Serializable {

    @EJB
    private OrcamentoDAO<Orcamento> dao;
    private Orcamento objeto;
    private Boolean editando;
    @EJB
    private PecasMaoDeObraDAO<PecasMaoDeObra> daoPecasMaoDeObra;
    @EJB
    private VeiculoDAO<Veiculo> daoVeiculo;
    private Boolean editandoItemOrcamento;
    private ItemOrcamento itemOrcamento;
    private Boolean novoItemOrcamento;

    public ControleOrcamento() {
        editando = false;
        editandoItemOrcamento = false;
    }

    public String listar() {
        editando = false;
        return "/privado/orcamento/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        editandoItemOrcamento = false;
        objeto = new Orcamento();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoItemOrcamento = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }

    }

    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro a remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");
            editando = false;
            editandoItemOrcamento = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }
    }

    public void novoItemOrcamento() {
        itemOrcamento = new ItemOrcamento();
        editandoItemOrcamento = true;
        novoItemOrcamento = true;
    }

    public void salvarItemOrcamento() {
        itemOrcamento.setValorTotal(itemOrcamento.getQuantidade()*itemOrcamento.getItem().getPreco());
        if (itemOrcamento.getId() == null) {
            if (novoItemOrcamento) {
                objeto.adicionarItem(itemOrcamento);
            }
        }
        editandoItemOrcamento = false;
        Util.mensagemInformacao("ItemOrcamento persistido com sucesso!");
    }

    public void alterarItemOrcamento(int index) {
        itemOrcamento = objeto.getItens().get(index);
        editandoItemOrcamento = true;
        novoItemOrcamento = false;
    }

    public void excluirItemOrcamento(int index) {
        objeto.removerItem(index);
        Util.mensagemInformacao("ItemOrcamento removido com sucesso!");
    }

    public Orcamento getObjeto() {
        return objeto;
    }

    public void setObjeto(Orcamento objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public OrcamentoDAO<Orcamento> getDao() {
        return dao;
    }

    public void setDao(OrcamentoDAO<Orcamento> dao) {
        this.dao = dao;
    }

    public PecasMaoDeObraDAO<PecasMaoDeObra> getDaoPecasMaoDeObra() {
        return daoPecasMaoDeObra;
    }

    public void setDaoPecasMaoDeObra(PecasMaoDeObraDAO<PecasMaoDeObra> daoPecasMaoDeObra) {
        this.daoPecasMaoDeObra = daoPecasMaoDeObra;
    }

    public VeiculoDAO<Veiculo> getDaoVeiculo() {
        return daoVeiculo;
    }

    public void setDaoVeiculo(VeiculoDAO<Veiculo> daoVeiculo) {
        this.daoVeiculo = daoVeiculo;
    }

    public Boolean getEditandoItemOrcamento() {
        return editandoItemOrcamento;
    }

    public void setEditandoItemOrcamento(Boolean editandoItemOrcamento) {
        this.editandoItemOrcamento = editandoItemOrcamento;
    }

    public ItemOrcamento getItemOrcamento() {
        return itemOrcamento;
    }

    public void setItemOrcamento(ItemOrcamento itemOrcamento) {
        this.itemOrcamento = itemOrcamento;
    }

    public Boolean getNovoItemOrcamento() {
        return novoItemOrcamento;
    }

    public void setNovoItemOrcamento(Boolean novoItemOrcamento) {
        this.novoItemOrcamento = novoItemOrcamento;
    }
}